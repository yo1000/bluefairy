/**
 *
 * Filter on the Table 1.0 - Filter field on table headers.
 * Version 1.0
 * @requires jQuery v1.7.0
 * 
 * http://www.opensource.org/licenses/mit-license.php
 * The MIT License (MIT)
 * 
 * Copyright (c) 2014 Fabiano Araujo
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

(function ($){
  $.fn.fott = function(custom){
    var options = $.extend({
      strict          : false,
      wrapperClasses  : 'filter',
      inputClasses    : 'input-fott',
      fieldFilter     : $(this).html().toLowerCase(),
      placeholder     : 'Filter for ' + $(this).html(),
      table           : $(this).parent().parent().parent()
    }, custom),
    html    = {
      div : $('<div></div>'),
      input: $('<input type="text">')
    },
    mustHave = {
      wrapperClass : 'filter',
      inputClass   : 'input-fott'
    };

    if (options.wrapperClasses.indexOf(mustHave.wrapperClass) < 0)
      options.wrapperClasses = options.wrapperClasses.trim() + ' ' +  mustHave.wrapperClass;

    if (options.inputClasses.indexOf(mustHave.inputClass) < 0)
      options.inputClasses = options.inputClasses.trim() + ' ' +  mustHave.inputClass;

    options.rows = options.table.find('tbody').find('tr');

    // Building div and input
    html.div.addClass(options.wrapperClasses);
    html.input.addClass(options.inputClasses);
    html.input.attr('data-fieldfilter', options.fieldFilter);
    html.input.attr('placeholder', options.placeholder);
    html.div.html(html.input);

    // Attach events to input field   
    html.input.keyup(function(){
      var value = $(this).val().toLowerCase();

      // If value is empty, there is no need
      // for loop through everything
      if (value.length === 0){
        $('.input-fott').val('');
        options.rows.show();
        return;
      }

      // Adding filtering class to clear
      // other fields when something is being
      // typed into other field
      $('.input-fott').removeClass('filtering');
      $(this).addClass('filtering');

      $('.input-fott').each(function(e){
          if (!$(this).hasClass('filtering'))
              $(this).val('');
      });

      // Core loop
      options.rows.each(function(){
          var td      = $(this).find('.' + options.fieldFilter),
              lookAt;

          // Check for the element that has value to be compared
          lookAt = td.hasClass('filter-value') ? td : td.find('.filter-value');

          // Search based on strict value
          if (options.strict)
              if (lookAt.html().toLowerCase() == value.trim())
                  $(this).show();
              else
                  $(this).hide();
          else
              if (lookAt.html().toLowerCase().indexOf(value.trim()) >= 0)
                  $(this).show();
              else
                  $(this).hide();
      });
    });

    // Creating html elements after each thead
    // th content
    $(this).append(html.div);

    return this;
  };
}(jQuery));