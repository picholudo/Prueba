import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EstadisticaFABComponent } from './estadistica-fab.component';
import { EstadisticaFABDetailComponent } from './estadistica-fab-detail.component';
import { EstadisticaFABUpdateComponent } from './estadistica-fab-update.component';
import { EstadisticaFABDeleteDialogComponent } from './estadistica-fab-delete-dialog.component';
import { estadisticaFABRoute } from './estadistica-fab.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(estadisticaFABRoute)],
  declarations: [
    EstadisticaFABComponent,
    EstadisticaFABDetailComponent,
    EstadisticaFABUpdateComponent,
    EstadisticaFABDeleteDialogComponent
  ],
  entryComponents: [EstadisticaFABDeleteDialogComponent]
})
export class AppEstadisticaFABModule {}
