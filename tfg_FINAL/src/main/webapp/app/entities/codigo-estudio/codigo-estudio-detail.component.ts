import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICodigoEstudio } from 'app/shared/model/codigo-estudio.model';

@Component({
  selector: 'jhi-codigo-estudio-detail',
  templateUrl: './codigo-estudio-detail.component.html'
})
export class CodigoEstudioDetailComponent implements OnInit {
  codigoEstudio: ICodigoEstudio | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ codigoEstudio }) => {
      this.codigoEstudio = codigoEstudio;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
