import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EstadisticaPzNeuronormaComponent } from './estadistica-pz-neuronorma.component';
import { EstadisticaPzNeuronormaDetailComponent } from './estadistica-pz-neuronorma-detail.component';
import { EstadisticaPzNeuronormaUpdateComponent } from './estadistica-pz-neuronorma-update.component';
import { EstadisticaPzNeuronormaDeleteDialogComponent } from './estadistica-pz-neuronorma-delete-dialog.component';
import { estadisticaPzNeuronormaRoute } from './estadistica-pz-neuronorma.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(estadisticaPzNeuronormaRoute)],
  declarations: [
    EstadisticaPzNeuronormaComponent,
    EstadisticaPzNeuronormaDetailComponent,
    EstadisticaPzNeuronormaUpdateComponent,
    EstadisticaPzNeuronormaDeleteDialogComponent
  ],
  entryComponents: [EstadisticaPzNeuronormaDeleteDialogComponent]
})
export class AppEstadisticaPzNeuronormaModule {}
