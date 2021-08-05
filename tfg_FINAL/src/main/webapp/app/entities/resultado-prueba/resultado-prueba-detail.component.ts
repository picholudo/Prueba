import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResultadoPrueba } from 'app/shared/model/resultado-prueba.model';

@Component({
  selector: 'jhi-resultado-prueba-detail',
  templateUrl: './resultado-prueba-detail.component.html'
})
export class ResultadoPruebaDetailComponent implements OnInit {
  resultadoPrueba: IResultadoPrueba | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resultadoPrueba }) => {
      this.resultadoPrueba = resultadoPrueba;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
