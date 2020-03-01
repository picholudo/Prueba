import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EstadisticasComponent } from 'app/entities/estadisticas/estadisticas.component';
import { EstadisticasService } from 'app/entities/estadisticas/estadisticas.service';
import { Estadisticas } from 'app/shared/model/estadisticas.model';

describe('Component Tests', () => {
  describe('Estadisticas Management Component', () => {
    let comp: EstadisticasComponent;
    let fixture: ComponentFixture<EstadisticasComponent>;
    let service: EstadisticasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticasComponent],
        providers: []
      })
        .overrideTemplate(EstadisticasComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticasComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticasService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Estadisticas(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.estadisticas && comp.estadisticas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
