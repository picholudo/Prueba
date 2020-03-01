import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { PuntuacionPruebaComponent } from './puntuacion-prueba.component';
import { PuntuacionPruebaDetailComponent } from './puntuacion-prueba-detail.component';
import { PuntuacionPruebaUpdateComponent } from './puntuacion-prueba-update.component';
import { PuntuacionPruebaDeleteDialogComponent } from './puntuacion-prueba-delete-dialog.component';
import { puntuacionPruebaRoute } from './puntuacion-prueba.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(puntuacionPruebaRoute)],
  declarations: [
    PuntuacionPruebaComponent,
    PuntuacionPruebaDetailComponent,
    PuntuacionPruebaUpdateComponent,
    PuntuacionPruebaDeleteDialogComponent
  ],
  entryComponents: [PuntuacionPruebaDeleteDialogComponent]
})
export class AppPuntuacionPruebaModule {}
