import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ZScoreDetailComponent } from 'app/entities/z-score/z-score-detail.component';
import { ZScore } from 'app/shared/model/z-score.model';

describe('Component Tests', () => {
  describe('ZScore Management Detail Component', () => {
    let comp: ZScoreDetailComponent;
    let fixture: ComponentFixture<ZScoreDetailComponent>;
    const route = ({ data: of({ zScore: new ZScore(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ZScoreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ZScoreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ZScoreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load zScore on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.zScore).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
