import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { DashboardDemoComponent } from './demo/view/dashboarddemo.component';
import { FormLayoutDemoComponent } from './demo/view/formlayoutdemo.component';
import { FloatLabelDemoComponent } from './demo/view/floatlabeldemo.component';
import { InvalidStateDemoComponent } from './demo/view/invalidstatedemo.component';
import { PanelsDemoComponent } from './demo/view/panelsdemo.component';
import { OverlaysDemoComponent } from './demo/view/overlaysdemo.component';
import { MediaDemoComponent } from './demo/view/mediademo.component';
import { MessagesDemoComponent } from './demo/view/messagesdemo.component';
import { MiscDemoComponent } from './demo/view/miscdemo.component';
import { EmptyDemoComponent } from './demo/view/emptydemo.component';
import { ChartsDemoComponent } from './demo/view/chartsdemo.component';
import { FileDemoComponent } from './demo/view/filedemo.component';
import { DocumentationComponent } from './demo/view/documentation.component';
import { AppMainComponent } from './app.main.component';
import { AppNotfoundComponent } from './pages/app.notfound.component';
import { AppErrorComponent } from './pages/app.error.component';
import { AppAccessdeniedComponent } from './pages/app.accessdenied.component';
import { AppLoginComponent } from './pages/app.login.component';
import { InputDemoComponent } from './demo/view/inputdemo.component';
import { ButtonDemoComponent } from './demo/view/buttondemo.component';
import { TableDemoComponent } from './demo/view/tabledemo.component';
import { ListDemoComponent } from './demo/view/listdemo.component';
import { TreeDemoComponent } from './demo/view/treedemo.component';
import { IconsComponent } from './utilities/icons.component';
import { AppCrudComponent } from './pages/app.crud.component';
import { AppCalendarComponent } from './pages/app.calendar.component';
import { AppTimelineDemoComponent } from './pages/app.timelinedemo.component';
import { AppInvoiceComponent } from './pages/app.invoice.component';
import { AppHelpComponent } from './pages/app.help.component';
import { BlocksComponent } from './blocks/blocks/blocks.component';
import { ContactsComponent } from './components/contacts/contacts.component';
import { AddContactsComponent } from './components/add-contacts/add-contacts.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { AuthorizationGuard } from './guards/authorization.guard';
import { ProfileComponent } from './components/profile/profile.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { AnalyticsComponent } from './components/analytics/analytics.component';
import { CampaingsComponent } from './components/campaings/campaings.component';
import { AddCampaignComponent } from './components/add-campaign/add-campaign.component';
import { SettingsComponent } from './components/settings/settings.component';
import { EngagementsComponent } from './components/engagements/engagements.component';

@NgModule({
    imports: [
        RouterModule.forRoot([
            { path: '', component: AppLoginComponent },
            { path: 'resetpassword', component: ResetPasswordComponent },
            {
                path: 'dashboard', component: AppMainComponent, //canActivate: [AuthorizationGuard],
                children: [
                    { path: '', component: DashboardDemoComponent },
                    { path: 'contacts', component: ContactsComponent },
                    { path: 'password', component: ChangePasswordComponent },
                    { path: 'add-contact', component: AddContactsComponent },
                    { path: 'profile', component: ProfileComponent },
                    { path: 'insights', component: AnalyticsComponent },
                    { path: 'campaigns', component: CampaingsComponent },
                    { path: 'add-campaign', component: AddCampaignComponent },
                    { path: 'setting', component: SettingsComponent },
                    { path: 'engagement', component: EngagementsComponent },
                    { path: 'uikit/formlayout', component: FormLayoutDemoComponent },
                    { path: 'uikit/floatlabel', component: FloatLabelDemoComponent },
                    { path: 'uikit/invalidstate', component: InvalidStateDemoComponent },
                    { path: 'uikit/input', component: InputDemoComponent },
                    { path: 'uikit/button', component: ButtonDemoComponent },
                    { path: 'uikit/table', component: TableDemoComponent },
                    { path: 'uikit/list', component: ListDemoComponent },
                    { path: 'uikit/tree', component: TreeDemoComponent },
                    { path: 'uikit/panel', component: PanelsDemoComponent },
                    { path: 'uikit/overlay', component: OverlaysDemoComponent },
                    { path: 'uikit/menu', loadChildren: () => import('./demo/view/menus/menus.module').then(m => m.MenusModule) },
                    { path: 'uikit/media', component: MediaDemoComponent },
                    { path: 'uikit/message', component: MessagesDemoComponent },
                    { path: 'uikit/misc', component: MiscDemoComponent },
                    { path: 'uikit/charts', component: ChartsDemoComponent },
                    { path: 'uikit/file', component: FileDemoComponent },
                    { path: 'utilities/icons', component: IconsComponent },
                    { path: 'pages/crud', component: AppCrudComponent },
                    { path: 'pages/calendar', component: AppCalendarComponent },
                    { path: 'pages/timeline', component: AppTimelineDemoComponent },
                    { path: 'pages/invoice', component: AppInvoiceComponent },
                    { path: 'pages/help', component: AppHelpComponent },
                    { path: 'pages/empty', component: EmptyDemoComponent },
                    { path: 'documentation', component: DocumentationComponent },
                    { path: 'blocks', component: BlocksComponent },
                ]
            },
            { path: 'error', component: AppErrorComponent },
            { path: 'access', component: AppAccessdeniedComponent },
            { path: 'notfound', component: AppNotfoundComponent },
            { path: '**', redirectTo: '/notfound' },
        ], { scrollPositionRestoration: 'enabled', useHash: false })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
