import { Component, OnInit, OnDestroy, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IUserInfo } from 'app/shared/model/user-info.model';
import { AccountService } from 'app/core';
import { FileUploader } from 'ng2-file-upload';
import { ITEMS_PER_PAGE } from 'app/shared';
import { UserInfoService } from './user-info.service';
import { SERVER_API_URL } from 'app/app.constants';
const URL = SERVER_API_URL + 'api/user-infos';
@Component({
    selector: 'jhi-user-info',
    templateUrl: './user-info.component.html',
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserInfoComponent implements OnInit, OnDestroy {
    private timer; //定时器
    currentAccount: any;
    userInfos: any;
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    phone: string;

    constructor(
        protected userInfoService: UserInfoService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected router: Router,
        protected eventManager: JhiEventManager,
        private ref: ChangeDetectorRef
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.timer = setInterval(() => {
            //设置定时刷新事件，每隔5秒刷新
            this.loadAll();
            this.ref.detectChanges();
        }, 1000);
    }

    uploader: FileUploader = new FileUploader({ url: URL + '/Excelfile', itemAlias: 'file' });
    loadAll() {
        this.userInfoService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IUserInfo[]>) => this.paginateUserInfos(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/user-info'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/user-info',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        /*this.uploader.onAfterAddingFile = file => {
            file.withCredentials = false;
        };*/
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInUserInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IUserInfo) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInUserInfos() {
        this.eventSubscriber = this.eventManager.subscribe('userInfoListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateUserInfos(data: IUserInfo[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.userInfos = data;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    querys() {
        alert(this.phone);
        if (this.phone === undefined || this.phone === '') {
            this.ngOnInit();
        } else {
            this.userInfoService.vagues(this.phone).subscribe(data => {
                this.userInfos = data;
            });
        }
    }
    exports() {
        this.userInfoService.vg(this.phone).subscribe(data => {
            const link = document.createElement('a');
            const blob = new Blob([data], { type: 'application/vnd.ms-excel' });
            link.setAttribute('href', window.URL.createObjectURL(blob));
            link.setAttribute('download', 'UserInfo' + '.xls');
            link.style.visibility = 'hidden';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        });
    }
    sd(phone: string) {
        this.userInfoService.vag(phone).subscribe(data => {
            this.userInfos = data;
            this.loadAll();
        });
    }
}
