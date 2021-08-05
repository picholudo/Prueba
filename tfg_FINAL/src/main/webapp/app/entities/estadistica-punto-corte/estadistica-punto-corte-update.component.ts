import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEstadisticaPuntoCorte, EstadisticaPuntoCorte } from 'app/shared/model/estadistica-punto-corte.model';
import { EstadisticaPuntoCorteService } from './estadistica-punto-corte.service';
import { IPrueba } from 'app/shared/model/prueba.model';
import { PruebaService } from 'app/entities/prueba/prueba.service';

@Component({
  selector: 'jhi-estadistica-punto-corte-update',
  templateUrl: './estadistica-punto-corte-update.component.html'
})
export class EstadisticaPuntoCorteUpdateComponent implements OnInit {
  isSaving = false;

  pruebas: IPrueba[] = [];

  editForm = this.fb.group({
    id: [],
    puntoCorte: [null, [Validators.required, Validators.min(0)]],
    pruebaId: [null, Validators.required]
  });

  constructor(
    protected estadisticaPuntoCorteService: EstadisticaPuntoCorteService,
    protected pruebaService: PruebaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaPuntoCorte }) => {
      this.updateForm(estadisticaPuntoCorte);

      this.pruebaService
        .query({tipoPrueba: 'PUNTOS_DE_CORTE'})
        .pipe(
          map((res: HttpResponse<IPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPrueba[]) => (this.pruebas = resBody));
    });
  }

  updateForm(estadisticaPuntoCorte: IEstadisticaPuntoCorte): void {
    this.editForm.patchValue({
      id: estadisticaPuntoCorte.id,
      puntoCorte: estadisticaPuntoCorte.puntoCorte,
      pruebaId: estadisticaPuntoCorte.pruebaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadisticaPuntoCorte = this.createFromForm();
    if (estadisticaPuntoCorte.id !== undefined) {
      this.subscribeToSaveResponse(this.estadisticaPuntoCorteService.update(estadisticaPuntoCorte));
    } else {
      this.subscribeToSaveResponse(this.estadisticaPuntoCorteService.create(estadisticaPuntoCorte));
    }
  }

  private createFromForm(): IEstadisticaPuntoCorte {
    return {
      ...new EstadisticaPuntoCorte(),
      id: this.editForm.get(['id'])!.value,
      puntoCorte: this.editForm.get(['puntoCorte'])!.value,
      pruebaId: this.editForm.get(['pruebaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadisticaPuntoCorte>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IPrueba): any {
    return item.id;
  }
}
