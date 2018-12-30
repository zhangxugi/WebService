/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { UserInfoService } from 'app/entities/user-info/user-info.service';
import { IUserInfo, UserInfo } from 'app/shared/model/user-info.model';

describe('Service Tests', () => {
    describe('UserInfo Service', () => {
        let injector: TestBed;
        let service: UserInfoService;
        let httpMock: HttpTestingController;
        let elemDefault: IUserInfo;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(UserInfoService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new UserInfo(
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'image/png',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a UserInfo', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new UserInfo(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a UserInfo', async () => {
                const returnedFromService = Object.assign(
                    {
                        userid: 1,
                        phone: 'BBBBBB',
                        isregister: 'BBBBBB',
                        status: 'BBBBBB',
                        logintime: 'BBBBBB',
                        username: 'BBBBBB',
                        firstname: 'BBBBBB',
                        lastname: 'BBBBBB',
                        isimage: 'BBBBBB',
                        remark: 'BBBBBB',
                        portrait: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of UserInfo', async () => {
                const returnedFromService = Object.assign(
                    {
                        userid: 1,
                        phone: 'BBBBBB',
                        isregister: 'BBBBBB',
                        status: 'BBBBBB',
                        logintime: 'BBBBBB',
                        username: 'BBBBBB',
                        firstname: 'BBBBBB',
                        lastname: 'BBBBBB',
                        isimage: 'BBBBBB',
                        remark: 'BBBBBB',
                        portrait: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a UserInfo', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
