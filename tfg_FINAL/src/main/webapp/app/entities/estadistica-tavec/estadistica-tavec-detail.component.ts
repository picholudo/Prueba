import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadisticaTAVEC } from 'app/shared/model/estadistica-tavec.model';

@Component({
  selector: 'jhi-estadistica-tavec-detail',
  templateUrl: './estadistica-tavec-detail.component.html'
})
export class EstadisticaTAVECDetailComponent implements OnInit {
  estadisticaTAVEC: IEstadisticaTAVEC | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaTAVEC }) => {
      this.estadisticaTAVEC = estadisticaTAVEC;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
