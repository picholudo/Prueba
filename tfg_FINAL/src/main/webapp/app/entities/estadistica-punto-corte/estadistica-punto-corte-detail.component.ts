import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadisticaPuntoCorte } from 'app/shared/model/estadistica-punto-corte.model';

@Component({
  selector: 'jhi-estadistica-punto-corte-detail',
  templateUrl: './estadistica-punto-corte-detail.component.html'
})
export class EstadisticaPuntoCorteDetailComponent implements OnInit {
  estadisticaPuntoCorte: IEstadisticaPuntoCorte | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaPuntoCorte }) => {
      this.estadisticaPuntoCorte = estadisticaPuntoCorte;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
