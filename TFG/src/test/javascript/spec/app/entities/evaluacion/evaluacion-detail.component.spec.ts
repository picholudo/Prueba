import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EvaluacionDetailComponent } from 'app/entities/evaluacion/evaluacion-detail.component';
import { Evaluacion } from 'app/shared/model/evaluacion.model';

describe('Component Tests', () => {
  describe('Evaluacion Management Detail Component', () => {
    let comp: EvaluacionDetailComponent;
    let fixture: ComponentFixture<EvaluacionDetailComponent>;
    const route = ({ data: of({ evaluacion: new Evaluacion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EvaluacionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EvaluacionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EvaluacionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load evaluacion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.evaluacion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
