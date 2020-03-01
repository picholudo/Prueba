import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EvaluacionComponent } from 'app/entities/evaluacion/evaluacion.component';
import { EvaluacionService } from 'app/entities/evaluacion/evaluacion.service';
import { Evaluacion } from 'app/shared/model/evaluacion.model';

describe('Component Tests', () => {
  describe('Evaluacion Management Component', () => {
    let comp: EvaluacionComponent;
    let fixture: ComponentFixture<EvaluacionComponent>;
    let service: EvaluacionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EvaluacionComponent],
        providers: []
      })
        .overrideTemplate(EvaluacionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EvaluacionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EvaluacionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Evaluacion(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.evaluacions && comp.evaluacions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
