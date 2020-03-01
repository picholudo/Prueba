import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadisticas } from 'app/shared/model/estadisticas.model';

@Component({
  selector: 'jhi-estadisticas-detail',
  templateUrl: './estadisticas-detail.component.html'
})
export class EstadisticasDetailComponent implements OnInit {
  estadisticas: IEstadisticas | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticas }) => {
      this.estadisticas = estadisticas;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
