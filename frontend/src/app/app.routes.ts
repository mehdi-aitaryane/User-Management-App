import { Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { AdminProfileComponent } from './component/admin-profile/admin-profile.component';
import { adminGuard } from './guard/admin.guard';
import { visitorGuard } from './guard/visitor.guard';
import { UserProfileComponent } from './component/user-profile/user-profile.component';
import { userGuard } from './guard/user.guard';
import { HomeComponent } from './component/home/home.component';
import { RegisterComponent } from './component/register/register.component';
import { AdminEditProfileComponent } from './component/admin-edit-profile/admin-edit-profile.component';
import { AdminChangePasswordComponent } from './component/admin-change-password/admin-change-password.component';
import { AdminUsersComponent } from './component/admin-users/admin-users.component';
import { UserEditProfileComponent } from './component/user-edit-profile/user-edit-profile.component';
import { UserChangePasswordComponent } from './component/user-change-password/user-change-password.component';
import { UserComponent } from './component/user/user.component';
import { AdminComponent } from './component/admin/admin.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login', component: LoginComponent, canActivate:[visitorGuard] },
    { path: 'register', component: RegisterComponent, canActivate:[visitorGuard] },
    { path: 'admin', component: AdminComponent, canActivate:[adminGuard] },
    { path: 'admin/users', component: AdminUsersComponent, canActivate:[adminGuard] },
    { path: 'admin/profile', component: AdminProfileComponent, canActivate:[adminGuard] },
    { path: 'admin/profile/edit', component: AdminEditProfileComponent, canActivate:[adminGuard] },
    { path: 'admin/profile/password', component: AdminChangePasswordComponent, canActivate:[adminGuard] },
    { path: 'user', component: UserComponent, canActivate:[userGuard] },
    { path: 'user/profile', component: UserProfileComponent, canActivate:[userGuard] },
    { path: 'user/profile/edit', component: UserEditProfileComponent, canActivate:[userGuard] },
    { path: 'user/profile/password', component: UserChangePasswordComponent, canActivate:[userGuard] },
];
