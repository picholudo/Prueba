import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { ResultadoPruebaComponent } from './resultado-prueba.component';
import { ResultadoPruebaDetailComponent } from './resultado-prueba-detail.component';
import { ResultadoPruebaUpdateComponent } from './resultado-prueba-update.component';
import { ResultadoPruebaDeleteDialogComponent } from './resultado-prueba-delete-dialog.component';
import { resultadoPruebaRoute } from './resultado-prueba.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(resultadoPruebaRoute)],
  declarations: [
    ResultadoPruebaComponent,
    ResultadoPruebaDetailComponent,
    ResultadoPruebaUpdateComponent,
    ResultadoPruebaDeleteDialogComponent
  ],
  entryComponents: [ResultadoPruebaDeleteDialogComponent]
})
export class AppResultadoPruebaModule {}
