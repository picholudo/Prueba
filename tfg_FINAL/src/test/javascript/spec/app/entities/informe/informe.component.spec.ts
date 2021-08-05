import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { InformeComponent } from 'app/entities/informe/informe.component';
import { InformeService } from 'app/entities/informe/informe.service';
import { Informe } from 'app/shared/model/informe.model';

describe('Component Tests', () => {
  describe('Informe Management Component', () => {
    let comp: InformeComponent;
    let fixture: ComponentFixture<InformeComponent>;
    let service: InformeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InformeComponent],
        providers: []
      })
        .overrideTemplate(InformeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InformeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InformeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Informe(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.informes && comp.informes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
