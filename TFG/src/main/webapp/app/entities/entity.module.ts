import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'puntos-corte',
        loadChildren: () => import('./puntos-corte/puntos-corte.module').then(m => m.AppPuntosCorteModule)
      },
      {
        path: 'z-score',
        loadChildren: () => import('./z-score/z-score.module').then(m => m.AppZScoreModule)
      },
      {
        path: 'paciente',
        loadChildren: () => import('./paciente/paciente.module').then(m => m.AppPacienteModule)
      },
      {
        path: 'evaluacion',
        loadChildren: () => import('./evaluacion/evaluacion.module').then(m => m.AppEvaluacionModule)
      },
      {
        path: 'prueba',
        loadChildren: () => import('./prueba/prueba.module').then(m => m.AppPruebaModule)
      },
      {
        path: 'estadisticas',
        loadChildren: () => import('./estadisticas/estadisticas.module').then(m => m.AppEstadisticasModule)
      },
      {
        path: 'puntuacion-prueba',
        loadChildren: () => import('./puntuacion-prueba/puntuacion-prueba.module').then(m => m.AppPuntuacionPruebaModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class AppEntityModule {}
