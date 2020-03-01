import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { PruebaComponent } from './prueba.component';
import { PruebaDetailComponent } from './prueba-detail.component';
import { PruebaUpdateComponent } from './prueba-update.component';
import { PruebaDeleteDialogComponent } from './prueba-delete-dialog.component';
import { pruebaRoute } from './prueba.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(pruebaRoute)],
  declarations: [PruebaComponent, PruebaDetailComponent, PruebaUpdateComponent, PruebaDeleteDialogComponent],
  entryComponents: [PruebaDeleteDialogComponent]
})
export class AppPruebaModule {}
