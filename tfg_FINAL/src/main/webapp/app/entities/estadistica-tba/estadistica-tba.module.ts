import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EstadisticaTBAComponent } from './estadistica-tba.component';
import { EstadisticaTBADetailComponent } from './estadistica-tba-detail.component';
import { EstadisticaTBAUpdateComponent } from './estadistica-tba-update.component';
import { EstadisticaTBADeleteDialogComponent } from './estadistica-tba-delete-dialog.component';
import { estadisticaTBARoute } from './estadistica-tba.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(estadisticaTBARoute)],
  declarations: [
    EstadisticaTBAComponent,
    EstadisticaTBADetailComponent,
    EstadisticaTBAUpdateComponent,
    EstadisticaTBADeleteDialogComponent
  ],
  entryComponents: [EstadisticaTBADeleteDialogComponent]
})
export class AppEstadisticaTBAModule {}
