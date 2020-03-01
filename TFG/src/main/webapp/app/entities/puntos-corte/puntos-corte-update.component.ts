import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPuntosCorte, PuntosCorte } from 'app/shared/model/puntos-corte.model';
import { PuntosCorteService } from './puntos-corte.service';

@Component({
  selector: 'jhi-puntos-corte-update',
  templateUrl: './puntos-corte-update.component.html'
})
export class PuntosCorteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required]],
    limite: [null, [Validators.required]],
    superarlo: [null, [Validators.required]]
  });

  constructor(protected puntosCorteService: PuntosCorteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ puntosCorte }) => {
      this.updateForm(puntosCorte);
    });
  }

  updateForm(puntosCorte: IPuntosCorte): void {
    this.editForm.patchValue({
      id: puntosCorte.id,
      nombre: puntosCorte.nombre,
      limite: puntosCorte.limite != null ? puntosCorte.limite.format(DATE_TIME_FORMAT) : null,
      superarlo: puntosCorte.superarlo
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const puntosCorte = this.createFromForm();
    if (puntosCorte.id !== undefined) {
      this.subscribeToSaveResponse(this.puntosCorteService.update(puntosCorte));
    } else {
      this.subscribeToSaveResponse(this.puntosCorteService.create(puntosCorte));
    }
  }

  private createFromForm(): IPuntosCorte {
    return {
      ...new PuntosCorte(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      limite: this.editForm.get(['limite'])!.value != null ? moment(this.editForm.get(['limite'])!.value, DATE_TIME_FORMAT) : undefined,
      superarlo: this.editForm.get(['superarlo'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPuntosCorte>>): void {
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
}
