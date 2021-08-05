import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';

@Component({
  selector: 'jhi-estadistica-ss-neuronorma-detail',
  templateUrl: './estadistica-ss-neuronorma-detail.component.html'
})
export class EstadisticaSSNeuronormaDetailComponent implements OnInit {
  estadisticaSSNeuronorma: IEstadisticaSSNeuronorma | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaSSNeuronorma }) => {
      this.estadisticaSSNeuronorma = estadisticaSSNeuronorma;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
