import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EstadisticaAjusteNeuronormaComponent } from './estadistica-ajuste-neuronorma.component';
import { EstadisticaAjusteNeuronormaDetailComponent } from './estadistica-ajuste-neuronorma-detail.component';
import { EstadisticaAjusteNeuronormaUpdateComponent } from './estadistica-ajuste-neuronorma-update.component';
import { EstadisticaAjusteNeuronormaDeleteDialogComponent } from './estadistica-ajuste-neuronorma-delete-dialog.component';
import { estadisticaAjusteNeuronormaRoute } from './estadistica-ajuste-neuronorma.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(estadisticaAjusteNeuronormaRoute)],
  declarations: [
    EstadisticaAjusteNeuronormaComponent,
    EstadisticaAjusteNeuronormaDetailComponent,
    EstadisticaAjusteNeuronormaUpdateComponent,
    EstadisticaAjusteNeuronormaDeleteDialogComponent
  ],
  entryComponents: [EstadisticaAjusteNeuronormaDeleteDialogComponent]
})
export class AppEstadisticaAjusteNeuronormaModule {}
