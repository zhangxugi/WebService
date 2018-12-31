import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TeleCheckSharedModule } from 'app/shared';
import {
    UserInfoComponent,
    UserInfoDetailComponent,
    UserInfoUpdateComponent,
    UserInfoDeletePopupComponent,
    UserInfoDeleteDialogComponent,
    userInfoRoute,
    userInfoPopupRoute
} from './';
import { FileUploadModule } from 'ng2-file-upload';

const ENTITY_STATES = [...userInfoRoute, ...userInfoPopupRoute];

@NgModule({
    imports: [TeleCheckSharedModule, RouterModule.forChild(ENTITY_STATES), FileUploadModule],
    declarations: [
        UserInfoComponent,
        UserInfoDetailComponent,
        UserInfoUpdateComponent,
        UserInfoDeleteDialogComponent,
        UserInfoDeletePopupComponent
    ],
    entryComponents: [UserInfoComponent, UserInfoUpdateComponent, UserInfoDeleteDialogComponent, UserInfoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TeleCheckUserInfoModule {}
