import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EvaluacionComponent } from './evaluacion.component';
import { EvaluacionDetailComponent } from './evaluacion-detail.component';
import { EvaluacionUpdateComponent } from './evaluacion-update.component';
import { EvaluacionDeleteDialogComponent } from './evaluacion-delete-dialog.component';
import { evaluacionRoute } from './evaluacion.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(evaluacionRoute)],
  declarations: [EvaluacionComponent, EvaluacionDetailComponent, EvaluacionUpdateComponent, EvaluacionDeleteDialogComponent],
  entryComponents: [EvaluacionDeleteDialogComponent]
})
export class AppEvaluacionModule {}
