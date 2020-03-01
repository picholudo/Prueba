import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EstadisticasComponent } from './estadisticas.component';
import { EstadisticasDetailComponent } from './estadisticas-detail.component';
import { EstadisticasUpdateComponent } from './estadisticas-update.component';
import { EstadisticasDeleteDialogComponent } from './estadisticas-delete-dialog.component';
import { estadisticasRoute } from './estadisticas.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(estadisticasRoute)],
  declarations: [EstadisticasComponent, EstadisticasDetailComponent, EstadisticasUpdateComponent, EstadisticasDeleteDialogComponent],
  entryComponents: [EstadisticasDeleteDialogComponent]
})
export class AppEstadisticasModule {}
