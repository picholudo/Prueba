import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInforme } from 'app/shared/model/informe.model';

@Component({
  selector: 'jhi-informe-detail',
  templateUrl: './informe-detail.component.html'
})
export class InformeDetailComponent implements OnInit {
  informe: IInforme | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ informe }) => {
      this.informe = informe;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
