import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'paciente',
        loadChildren: () => import('./paciente/paciente.module').then(m => m.AppPacienteModule)
      },
      {
        path: 'prueba',
        loadChildren: () => import('./prueba/prueba.module').then(m => m.AppPruebaModule)
      },
      {
        path: 'edad-tipo-prueba',
        loadChildren: () => import('./edad-tipo-prueba/edad-tipo-prueba.module').then(m => m.AppEdadTipoPruebaModule)
      },
      {
        path: 'informe',
        loadChildren: () => import('./informe/informe.module').then(m => m.AppInformeModule)
      },
      {
        path: 'codigo-estudio',
        loadChildren: () => import('./codigo-estudio/codigo-estudio.module').then(m => m.AppCodigoEstudioModule)
      },
      {
        path: 'estadistica-mmse',
        loadChildren: () => import('./estadistica-mmse/estadistica-mmse.module').then(m => m.AppEstadisticaMMSEModule)
      },
      {
        path: 'resultado-prueba',
        loadChildren: () => import('./resultado-prueba/resultado-prueba.module').then(m => m.AppResultadoPruebaModule)
      },
      {
        path: 'estadistica-ss-neuronorma',
        loadChildren: () =>
          import('./estadistica-ss-neuronorma/estadistica-ss-neuronorma.module').then(m => m.AppEstadisticaSSNeuronormaModule)
      },
      {
        path: 'estadistica-ajuste-neuronorma',
        loadChildren: () =>
          import('./estadistica-ajuste-neuronorma/estadistica-ajuste-neuronorma.module').then(m => m.AppEstadisticaAjusteNeuronormaModule)
      },
      {
        path: 'estadistica-pz-neuronorma',
        loadChildren: () =>
          import('./estadistica-pz-neuronorma/estadistica-pz-neuronorma.module').then(m => m.AppEstadisticaPzNeuronormaModule)
      },
      {
        path: 'estadistica-fab',
        loadChildren: () => import('./estadistica-fab/estadistica-fab.module').then(m => m.AppEstadisticaFABModule)
      },
      {
        path: 'estadistica-tavec',
        loadChildren: () => import('./estadistica-tavec/estadistica-tavec.module').then(m => m.AppEstadisticaTAVECModule)
      },
      {
        path: 'estadistica-tba',
        loadChildren: () => import('./estadistica-tba/estadistica-tba.module').then(m => m.AppEstadisticaTBAModule)
      },
      {
        path: 'estadistica-punto-corte',
        loadChildren: () => import('./estadistica-punto-corte/estadistica-punto-corte.module').then(m => m.AppEstadisticaPuntoCorteModule)
      },
      {
        path: 'grupo',
        loadChildren: () => import('./grupo/grupo.module').then(m => m.AppGrupoModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class AppEntityModule {}
