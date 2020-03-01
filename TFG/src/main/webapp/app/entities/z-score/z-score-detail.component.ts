import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IZScore } from 'app/shared/model/z-score.model';

@Component({
  selector: 'jhi-z-score-detail',
  templateUrl: './z-score-detail.component.html'
})
export class ZScoreDetailComponent implements OnInit {
  zScore: IZScore | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ zScore }) => {
      this.zScore = zScore;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
