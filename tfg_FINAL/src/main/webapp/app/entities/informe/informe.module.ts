import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { InformeComponent } from './informe.component';
import { InformeDetailComponent } from './informe-detail.component';
import { InformeUpdateComponent } from './informe-update.component';
import { InformeDeleteDialogComponent } from './informe-delete-dialog.component';
import { informeRoute } from './informe.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(informeRoute)],
  declarations: [InformeComponent, InformeDetailComponent, InformeUpdateComponent, InformeDeleteDialogComponent],
  entryComponents: [InformeDeleteDialogComponent]
})
export class AppInformeModule {}
