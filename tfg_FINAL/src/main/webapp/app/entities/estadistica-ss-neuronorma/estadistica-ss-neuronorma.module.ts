import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EstadisticaSSNeuronormaComponent } from './estadistica-ss-neuronorma.component';
import { EstadisticaSSNeuronormaDetailComponent } from './estadistica-ss-neuronorma-detail.component';
import { EstadisticaSSNeuronormaUpdateComponent } from './estadistica-ss-neuronorma-update.component';
import { EstadisticaSSNeuronormaDeleteDialogComponent } from './estadistica-ss-neuronorma-delete-dialog.component';
import { estadisticaSSNeuronormaRoute } from './estadistica-ss-neuronorma.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(estadisticaSSNeuronormaRoute)],
  declarations: [
    EstadisticaSSNeuronormaComponent,
    EstadisticaSSNeuronormaDetailComponent,
    EstadisticaSSNeuronormaUpdateComponent,
    EstadisticaSSNeuronormaDeleteDialogComponent
  ],
  entryComponents: [EstadisticaSSNeuronormaDeleteDialogComponent]
})
export class AppEstadisticaSSNeuronormaModule {}
