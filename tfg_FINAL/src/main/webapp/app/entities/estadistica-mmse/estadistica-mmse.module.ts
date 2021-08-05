import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EstadisticaMMSEComponent } from './estadistica-mmse.component';
import { EstadisticaMMSEDetailComponent } from './estadistica-mmse-detail.component';
import { EstadisticaMMSEUpdateComponent } from './estadistica-mmse-update.component';
import { EstadisticaMMSEDeleteDialogComponent } from './estadistica-mmse-delete-dialog.component';
import { estadisticaMMSERoute } from './estadistica-mmse.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(estadisticaMMSERoute)],
  declarations: [
    EstadisticaMMSEComponent,
    EstadisticaMMSEDetailComponent,
    EstadisticaMMSEUpdateComponent,
    EstadisticaMMSEDeleteDialogComponent
  ],
  entryComponents: [EstadisticaMMSEDeleteDialogComponent]
})
export class AppEstadisticaMMSEModule {}
