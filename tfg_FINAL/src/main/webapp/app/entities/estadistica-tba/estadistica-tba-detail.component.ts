import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadisticaTBA } from 'app/shared/model/estadistica-tba.model';

@Component({
  selector: 'jhi-estadistica-tba-detail',
  templateUrl: './estadistica-tba-detail.component.html'
})
export class EstadisticaTBADetailComponent implements OnInit {
  estadisticaTBA: IEstadisticaTBA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaTBA }) => {
      this.estadisticaTBA = estadisticaTBA;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
