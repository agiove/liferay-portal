AUI.add(
	'liferay-item-selector-dialog',
	function(A) {
		var Lang = A.Lang;

		var Util = Liferay.Util;

		var STR_EVENT_NAME = 'eventName';

		var STR_SELECTED_ITEM = 'selectedItem';

		var LiferayItemSelectorDialog = A.Component.create(
			{
				ATTRS: {
					eventName: {
						validator: Lang.isString
					},

					selectedItem: {
					},

					strings: {
						value: {
							add: Liferay.Language.get('add'),
							cancel: Liferay.Language.get('cancel')
						}
					},

					title: {
						validator: Lang.isString,
						value: Liferay.Language.get('select-image')
					},

					url: {
						validator: Lang.isString
					},

					zIndex: {
						validator: Lang.isNumber
					}
				},

				NAME: 'item-selector-dialog',

				NS: 'item-selector-dialog',

				prototype: {
					close: function() {
						var instance = this;

						Util.getWindow(instance.get(STR_EVENT_NAME)).hide();
					},

					open: function() {
						var instance = this;

						var strings = instance.get('strings');

						var eventName = instance.get(STR_EVENT_NAME);

						var zIndex = instance.get('zIndex');

						Util.selectEntity(
							{
								dialog: {
									constrain: true,
									destroyOnHide: true,
									modal: true,
									'toolbars.footer': [
										{
											cssClass: 'btn-primary',
											disabled: true,
											id: 'addButton',
											label: strings.add,
											on: {
												click: function() {
													instance.set(STR_SELECTED_ITEM, instance._selectedItem);
													instance.close();
												}
											}
										},
										{
											cssClass: 'btn-link close-modal',
											id: 'cancelButton',
											label: strings.cancel,
											on: {
												click: function() {
													instance.set(STR_SELECTED_ITEM, null);
													instance.close();
												}
											}
										}
									],
									zIndex: zIndex
								},
								eventName: eventName,
								id: eventName,
								stack: !zIndex,
								title: instance.get('title'),
								uri: instance.get('url')
							},
							A.bind(instance._onItemSelected, instance)
						);
					},

					_onItemSelected: function(event) {
						var instance = this;

						var selectedItem = event.data;

						var dialog = Util.getWindow(instance.get(STR_EVENT_NAME));

						var addButton = dialog.getToolbar('footer').get('boundingBox').one('#addButton');

						Util.toggleDisabled(addButton, !selectedItem);

						instance._selectedItem = selectedItem;
					}
				}
			}
		);

		A.LiferayItemSelectorDialog = LiferayItemSelectorDialog;
	},
	'',
	{
		requires: ['aui-component']
	}
);