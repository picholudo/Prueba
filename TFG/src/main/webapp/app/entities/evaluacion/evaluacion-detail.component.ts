import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvaluacion } from 'app/shared/model/evaluacion.model';

@Component({
  selector: 'jhi-evaluacion-detail',
  templateUrl: './evaluacion-detail.component.html'
})
export class EvaluacionDetailComponent implements OnInit {
  evaluacion: IEvaluacion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evaluacion }) => {
      this.evaluacion = evaluacion;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
