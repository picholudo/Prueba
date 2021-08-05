import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';

@Component({
  selector: 'jhi-estadistica-ajuste-neuronorma-detail',
  templateUrl: './estadistica-ajuste-neuronorma-detail.component.html'
})
export class EstadisticaAjusteNeuronormaDetailComponent implements OnInit {
  estadisticaAjusteNeuronorma: IEstadisticaAjusteNeuronorma | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaAjusteNeuronorma }) => {
      this.estadisticaAjusteNeuronorma = estadisticaAjusteNeuronorma;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
