import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { EdadTipoPruebaComponent } from './edad-tipo-prueba.component';
import { EdadTipoPruebaDetailComponent } from './edad-tipo-prueba-detail.component';
import { EdadTipoPruebaUpdateComponent } from './edad-tipo-prueba-update.component';
import { EdadTipoPruebaDeleteDialogComponent } from './edad-tipo-prueba-delete-dialog.component';
import { edadTipoPruebaRoute } from './edad-tipo-prueba.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(edadTipoPruebaRoute)],
  declarations: [
    EdadTipoPruebaComponent,
    EdadTipoPruebaDetailComponent,
    EdadTipoPruebaUpdateComponent,
    EdadTipoPruebaDeleteDialogComponent
  ],
  entryComponents: [EdadTipoPruebaDeleteDialogComponent]
})
export class AppEdadTipoPruebaModule {}
