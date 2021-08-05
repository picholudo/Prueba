import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadisticaFAB } from 'app/shared/model/estadistica-fab.model';

@Component({
  selector: 'jhi-estadistica-fab-detail',
  templateUrl: './estadistica-fab-detail.component.html'
})
export class EstadisticaFABDetailComponent implements OnInit {
  estadisticaFAB: IEstadisticaFAB | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaFAB }) => {
      this.estadisticaFAB = estadisticaFAB;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
