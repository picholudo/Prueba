import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IZScore, ZScore } from 'app/shared/model/z-score.model';
import { ZScoreService } from './z-score.service';

@Component({
  selector: 'jhi-z-score-update',
  templateUrl: './z-score-update.component.html'
})
export class ZScoreUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required]]
  });

  constructor(protected zScoreService: ZScoreService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ zScore }) => {
      this.updateForm(zScore);
    });
  }

  updateForm(zScore: IZScore): void {
    this.editForm.patchValue({
      id: zScore.id,
      nombre: zScore.nombre
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const zScore = this.createFromForm();
    if (zScore.id !== undefined) {
      this.subscribeToSaveResponse(this.zScoreService.update(zScore));
    } else {
      this.subscribeToSaveResponse(this.zScoreService.create(zScore));
    }
  }

  private createFromForm(): IZScore {
    return {
      ...new ZScore(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IZScore>>): void {
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
