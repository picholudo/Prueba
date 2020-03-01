import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';

@Component({
  selector: 'jhi-puntuacion-prueba-detail',
  templateUrl: './puntuacion-prueba-detail.component.html'
})
export class PuntuacionPruebaDetailComponent implements OnInit {
  puntuacionPrueba: IPuntuacionPrueba | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ puntuacionPrueba }) => {
      this.puntuacionPrueba = puntuacionPrueba;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
