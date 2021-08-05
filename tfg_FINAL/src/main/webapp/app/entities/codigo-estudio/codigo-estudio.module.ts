import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { CodigoEstudioComponent } from './codigo-estudio.component';
import { CodigoEstudioDetailComponent } from './codigo-estudio-detail.component';
import { CodigoEstudioUpdateComponent } from './codigo-estudio-update.component';
import { CodigoEstudioDeleteDialogComponent } from './codigo-estudio-delete-dialog.component';
import { codigoEstudioRoute } from './codigo-estudio.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(codigoEstudioRoute)],
  declarations: [CodigoEstudioComponent, CodigoEstudioDetailComponent, CodigoEstudioUpdateComponent, CodigoEstudioDeleteDialogComponent],
  entryComponents: [CodigoEstudioDeleteDialogComponent]
})
export class AppCodigoEstudioModule {}
