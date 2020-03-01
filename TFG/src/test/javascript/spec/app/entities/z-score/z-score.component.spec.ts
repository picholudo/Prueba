import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { ZScoreComponent } from 'app/entities/z-score/z-score.component';
import { ZScoreService } from 'app/entities/z-score/z-score.service';
import { ZScore } from 'app/shared/model/z-score.model';

describe('Component Tests', () => {
  describe('ZScore Management Component', () => {
    let comp: ZScoreComponent;
    let fixture: ComponentFixture<ZScoreComponent>;
    let service: ZScoreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ZScoreComponent],
        providers: []
      })
        .overrideTemplate(ZScoreComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ZScoreComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ZScoreService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ZScore(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.zScores && comp.zScores[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
