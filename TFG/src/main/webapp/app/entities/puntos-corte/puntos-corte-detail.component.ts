import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPuntosCorte } from 'app/shared/model/puntos-corte.model';

@Component({
  selector: 'jhi-puntos-corte-detail',
  templateUrl: './puntos-corte-detail.component.html'
})
export class PuntosCorteDetailComponent implements OnInit {
  puntosCorte: IPuntosCorte | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ puntosCorte }) => {
      this.puntosCorte = puntosCorte;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
