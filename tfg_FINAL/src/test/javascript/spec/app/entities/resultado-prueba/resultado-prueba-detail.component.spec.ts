import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ResultadoPruebaDetailComponent } from 'app/entities/resultado-prueba/resultado-prueba-detail.component';
import { ResultadoPrueba } from 'app/shared/model/resultado-prueba.model';

describe('Component Tests', () => {
  describe('ResultadoPrueba Management Detail Component', () => {
    let comp: ResultadoPruebaDetailComponent;
    let fixture: ComponentFixture<ResultadoPruebaDetailComponent>;
    const route = ({ data: of({ resultadoPrueba: new ResultadoPrueba(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ResultadoPruebaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ResultadoPruebaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ResultadoPruebaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load resultadoPrueba on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.resultadoPrueba).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
