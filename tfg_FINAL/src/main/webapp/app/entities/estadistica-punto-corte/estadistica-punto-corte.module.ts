import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EstadisticaPuntoCorteComponent } from './estadistica-punto-corte.component';
import { EstadisticaPuntoCorteDetailComponent } from './estadistica-punto-corte-detail.component';
import { EstadisticaPuntoCorteUpdateComponent } from './estadistica-punto-corte-update.component';
import { EstadisticaPuntoCorteDeleteDialogComponent } from './estadistica-punto-corte-delete-dialog.component';
import { estadisticaPuntoCorteRoute } from './estadistica-punto-corte.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(estadisticaPuntoCorteRoute)],
  declarations: [
    EstadisticaPuntoCorteComponent,
    EstadisticaPuntoCorteDetailComponent,
    EstadisticaPuntoCorteUpdateComponent,
    EstadisticaPuntoCorteDeleteDialogComponent
  ],
  entryComponents: [EstadisticaPuntoCorteDeleteDialogComponent]
})
export class AppEstadisticaPuntoCorteModule {}
