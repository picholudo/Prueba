import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EstadisticaTAVECComponent } from './estadistica-tavec.component';
import { EstadisticaTAVECDetailComponent } from './estadistica-tavec-detail.component';
import { EstadisticaTAVECUpdateComponent } from './estadistica-tavec-update.component';
import { EstadisticaTAVECDeleteDialogComponent } from './estadistica-tavec-delete-dialog.component';
import { estadisticaTAVECRoute } from './estadistica-tavec.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(estadisticaTAVECRoute)],
  declarations: [
    EstadisticaTAVECComponent,
    EstadisticaTAVECDetailComponent,
    EstadisticaTAVECUpdateComponent,
    EstadisticaTAVECDeleteDialogComponent
  ],
  entryComponents: [EstadisticaTAVECDeleteDialogComponent]
})
export class AppEstadisticaTAVECModule {}
