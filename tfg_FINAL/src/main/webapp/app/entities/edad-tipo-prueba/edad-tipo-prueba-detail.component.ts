import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';

@Component({
  selector: 'jhi-edad-tipo-prueba-detail',
  templateUrl: './edad-tipo-prueba-detail.component.html'
})
export class EdadTipoPruebaDetailComponent implements OnInit {
  edadTipoPrueba: IEdadTipoPrueba | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ edadTipoPrueba }) => {
      this.edadTipoPrueba = edadTipoPrueba;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
