import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { PuntosCorteComponent } from './puntos-corte.component';
import { PuntosCorteDetailComponent } from './puntos-corte-detail.component';
import { PuntosCorteUpdateComponent } from './puntos-corte-update.component';
import { PuntosCorteDeleteDialogComponent } from './puntos-corte-delete-dialog.component';
import { puntosCorteRoute } from './puntos-corte.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(puntosCorteRoute)],
  declarations: [PuntosCorteComponent, PuntosCorteDetailComponent, PuntosCorteUpdateComponent, PuntosCorteDeleteDialogComponent],
  entryComponents: [PuntosCorteDeleteDialogComponent]
})
export class AppPuntosCorteModule {}
