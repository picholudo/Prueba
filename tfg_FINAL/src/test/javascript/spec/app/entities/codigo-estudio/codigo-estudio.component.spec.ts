import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { CodigoEstudioComponent } from 'app/entities/codigo-estudio/codigo-estudio.component';
import { CodigoEstudioService } from 'app/entities/codigo-estudio/codigo-estudio.service';
import { CodigoEstudio } from 'app/shared/model/codigo-estudio.model';

describe('Component Tests', () => {
  describe('CodigoEstudio Management Component', () => {
    let comp: CodigoEstudioComponent;
    let fixture: ComponentFixture<CodigoEstudioComponent>;
    let service: CodigoEstudioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CodigoEstudioComponent],
        providers: []
      })
        .overrideTemplate(CodigoEstudioComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CodigoEstudioComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CodigoEstudioService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CodigoEstudio(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.codigoEstudios && comp.codigoEstudios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
