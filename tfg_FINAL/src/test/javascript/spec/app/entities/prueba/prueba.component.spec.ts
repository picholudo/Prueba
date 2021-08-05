import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { PruebaComponent } from 'app/entities/prueba/prueba.component';
import { PruebaService } from 'app/entities/prueba/prueba.service';
import { Prueba } from 'app/shared/model/prueba.model';

describe('Component Tests', () => {
  describe('Prueba Management Component', () => {
    let comp: PruebaComponent;
    let fixture: ComponentFixture<PruebaComponent>;
    let service: PruebaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PruebaComponent],
        providers: []
      })
        .overrideTemplate(PruebaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PruebaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PruebaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Prueba(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.pruebas && comp.pruebas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
