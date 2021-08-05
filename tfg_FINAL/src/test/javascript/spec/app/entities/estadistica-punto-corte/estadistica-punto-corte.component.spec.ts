import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EstadisticaPuntoCorteComponent } from 'app/entities/estadistica-punto-corte/estadistica-punto-corte.component';
import { EstadisticaPuntoCorteService } from 'app/entities/estadistica-punto-corte/estadistica-punto-corte.service';
import { EstadisticaPuntoCorte } from 'app/shared/model/estadistica-punto-corte.model';

describe('Component Tests', () => {
  describe('EstadisticaPuntoCorte Management Component', () => {
    let comp: EstadisticaPuntoCorteComponent;
    let fixture: ComponentFixture<EstadisticaPuntoCorteComponent>;
    let service: EstadisticaPuntoCorteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaPuntoCorteComponent],
        providers: []
      })
        .overrideTemplate(EstadisticaPuntoCorteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaPuntoCorteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaPuntoCorteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EstadisticaPuntoCorte(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.estadisticaPuntoCortes && comp.estadisticaPuntoCortes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
