import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { ResultadoPruebaComponent } from 'app/entities/resultado-prueba/resultado-prueba.component';
import { ResultadoPruebaService } from 'app/entities/resultado-prueba/resultado-prueba.service';
import { ResultadoPrueba } from 'app/shared/model/resultado-prueba.model';

describe('Component Tests', () => {
  describe('ResultadoPrueba Management Component', () => {
    let comp: ResultadoPruebaComponent;
    let fixture: ComponentFixture<ResultadoPruebaComponent>;
    let service: ResultadoPruebaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ResultadoPruebaComponent],
        providers: []
      })
        .overrideTemplate(ResultadoPruebaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResultadoPruebaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResultadoPruebaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ResultadoPrueba(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.resultadoPruebas && comp.resultadoPruebas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
