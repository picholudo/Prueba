import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';

@Component({
  selector: 'jhi-estadistica-pz-neuronorma-detail',
  templateUrl: './estadistica-pz-neuronorma-detail.component.html'
})
export class EstadisticaPzNeuronormaDetailComponent implements OnInit {
  estadisticaPzNeuronorma: IEstadisticaPzNeuronorma | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaPzNeuronorma }) => {
      this.estadisticaPzNeuronorma = estadisticaPzNeuronorma;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
