import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadisticaMMSE } from 'app/shared/model/estadistica-mmse.model';

@Component({
  selector: 'jhi-estadistica-mmse-detail',
  templateUrl: './estadistica-mmse-detail.component.html'
})
export class EstadisticaMMSEDetailComponent implements OnInit {
  estadisticaMMSE: IEstadisticaMMSE | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaMMSE }) => {
      this.estadisticaMMSE = estadisticaMMSE;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
